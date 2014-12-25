function showPasswordForm(el)
{
	$(el).hide();
	$('#passwordForm-box').slideDown(function()
	{
		$(this).find('input')[0].focus();
	});
	
	return false;
}

function hidePasswordForm()
{
	$('#passwordForm-box').slideUp(function()
	{
		$('#change-password-control').show();
	});
	
	return false;	
}


function sendPassword()
{
	$.ajax({
		url: '/webAjax/users/change/',
		type: 'post',
		data: $('#changePasswordForm').serialize(),
		success: function()
		{
			$('#changePasswordForm').html('<p>Password was successfully changed.</p>');
		}
	});
	
	return false;
}

function sendForgot()
{	
	var data = $('#recoveryForm').serialize();
	$('#recoveryForm').html('<p>Password has been successfully sent to your email.</p>');

	$.ajax({
		url: '/webAjax/users/forgot/',
		type: 'post',
		data: data
	});
	
	return false;
}

function sendLogin(place, redir)
{
	var formID = '#loginForm';
	if (place === true) formID = '#loginFormPlace';


	$.ajax({
		url: '/webAjax/users/login/',
		type: 'post',
		data: $(formID).serialize(),
		success: function(data)
		{
			top.location.href = '/account/';
		}
	});
	
	return false;
}	

function sendReg(place, redir)
{
	var formID = '#regForm';
	if (place === true) formID = '#regFormPlace';

	var data = $(formID).serialize();

	$.ajax({
		url: '/webAjax/users/reg/',
		type: 'post',
		data: data,
		success: function()
		{
			$.ajax({ 
				url: '/webAjax/users/sendRegEmail/', 				
				type: 'post',
				data: 'user_email=' + $('#user_email').val()
			});
			
			setTimeout(function()
			{
				top.location.href = '/account/';
			}, 10);	
			
		}
	});
	
	
	return false;
}


(function($){

	// Initialization	
	$.fn.validate = function(options)
	{		
		var obj = new Construct(this, options);
		obj.init();
		return obj;
	};
	
	
	// Options and variables	
	function Construct(el, options) {

		this.opts = $.extend({
			url: '',
			callback: false,
			trigger: false,
			errorClassName: 'input-error'
		}, options);
		
		this.$el = $(el);
	};

	// Functionality
	Construct.prototype = {
		init: function()
		{	
			this.$el.submit(function() { return false });			
			$(this.opts.trigger).click($.proxy(this.validate, this));

		},
		validate: function()
		{
			// send form
			$.ajax({
				url: this.opts.url,
				type: 'post',
				data: this.$el.serialize(),
				success: $.proxy(function(data)
				{
					if (data)
					{
						var obj = $.parseJSON(data);
					
						this.hideErrors();					
						
						if (typeof obj.href != 'undefined')
						{
							top.location.href = obj.href;
						}
						else if (typeof obj.errors != 'undefined')
						{
							$.each(obj.errors, $.proxy(function(s, text)
							{
								var el = this.$el.find('[name=' + s + ']');
								$(el).addClass(this.opts.errorClassName);
							}, this));
						}
					}
					else
					{
						if (typeof this.opts.callback == 'function') this.opts.callback();
					}
					
				}, this)
			});
		},
		hideErrors: function()
		{
			this.$el.find('.' + this.opts.errorClassName).removeClass(this.opts.errorClassName);		
		},
		urldecode: function(str)
		{	
			return decodeURIComponent(str.replace(/\+/g, '%20'));
		},
		stripslashes: function(str)
		{  
			return (str+'').replace(/\0/g, '0').replace(/\\([\\'"])/g, '$1');
		}							
	};
		
})($);	