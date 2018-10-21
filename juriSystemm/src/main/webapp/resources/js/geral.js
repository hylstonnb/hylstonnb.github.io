function inserirChar(input) {

	var text = input.value;

	if (text.length == 13) {
		text = text.substr(0, 9) + "-" + text.substr(9, 12);
		input.value = text;
	}

	if (text.length == 14 || text.length == 15) {
		text = text.substr(0, 10) + "-" + text.substr(10, 13);
		input.value = text;
	}
}

function registerConfirmDialogHotkeys(dlgWidget) {
	 
    var key = 'keydown.pfdialog_' + dlgWidget.id;
 
    $(document).off(key).on(key, function (e) {
        var keyCode = $.ui.keyCode;
        var active = parseInt(dlgWidget.jq.css('z-index')) === PrimeFaces.zindex;
 
        if (dlgWidget.jq.hasClass('ui-overlay-visible') && active) {
            if (e.which === keyCode.ESCAPE) {
                dlgWidget.hide();
            } else if (e.which === keyCode.ENTER || e.which == keyCode.NUMPAD_ENTER) {
                dlgWidget.jq.find('button:submit:first').click();
                e.preventDefault();
            }
        }
    });
}

function fecharDialog(){

    var closeable;
    var indexHighest = 0;
    jQuery(".ui-dialog.ui-widget.ui-widget-content.ui-overlay-visible").each(function(){
        var indexCurrent = parseInt($(this).css("zIndex"), 10);
        if (indexCurrent > indexHighest) {
            indexHighest = indexCurrent;
            closeable = this;
        }
    });
    
    if (closeable != null) {
    
        jQuery(closeable).removeClass('ui-overlay-visible').addClass('ui-overlay-hidden');
        jQuery(closeable).css({'visibility': '', 'z-index': '', 'display': ''});
		var modal = '#' + jQuery(closeable).attr('id') + '_modal';
		modal = modal.split(':');
		modal = modal[modal.length -1];
        jQuery('div[id$='+ modal +']').hide();
    }
}