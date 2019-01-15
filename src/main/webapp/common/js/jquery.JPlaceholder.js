/*
 * jQuery placeholder, fix for IE6,7,8,9
 */
$(function(){
    // -- Constants --
    var PLACE_HOLDER_COLOR = "rgb(169,169,169)"; // "darkGrey" does not work in IE6
    var PLACE_HOLDER_DATA_NAME = "original-font-color";
          
    // -- Util Methods --  
    var getContent = function(element){
        return $(element).val();       
    }
      
    var setContent = function(element, content){
        $(element).val(content);       
    }
          
    var getPlaceholder = function(element){
        return $(element).attr("placeholder");
    }
          
    var isContentEmpty = function(element){
        var content = getContent(element);
        return (content.length === 0) || content == getPlaceholder(element);
    }
              
    var setPlaceholderStyle = function(element){
        $(element).data(PLACE_HOLDER_DATA_NAME, $(element).css("color"));
        $(element).css("color", PLACE_HOLDER_COLOR);       
    }
          
    var clearPlaceholderStyle = function(element){
        $(element).css("color", $(element).data(PLACE_HOLDER_DATA_NAME));      
        $(element).removeData(PLACE_HOLDER_DATA_NAME);
    }
          
    var showPlaceholder = function(element){
        setContent(element, getPlaceholder(element));
        setPlaceholderStyle(element);  
    }
          
    var hidePlaceholder = function(element){
        if($(element).data(PLACE_HOLDER_DATA_NAME)){
            setContent(element, "");
            clearPlaceholderStyle(element);
        }
    }
          
    // -- Event Handlers --
    var inputFocused = function(){
        if(isContentEmpty(this)){
            hidePlaceholder(this);     
        }
    }
          
    var inputBlurred = function(){
        if(isContentEmpty(this)){
            showPlaceholder(this);
        }
    }
          
    var parentFormSubmitted = function(){
        if(isContentEmpty(this)){
            hidePlaceholder(this);     
        }  
    }
          
    // -- Bind event to components --
    if('placeholder' in document.createElement('input')==false){
    $("textarea, input[type='text']").each(function(index, element){
        if($(element).attr("placeholder")){
            $(element).focus(inputFocused);
            $(element).blur(inputBlurred);
            $(element).bind("parentformsubmitted", parentFormSubmitted);
                  
            // triggers show place holder on page load
            $(element).trigger("blur");
            // triggers form submitted event on parent form submit
            $(element).parents("form").submit(function(){
                $(element).trigger("parentformsubmitted");
            });
        }
    });
    }
});