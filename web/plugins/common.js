function setSidebarActive(th){
    $(th).parent("li").siblings().removeClass("active");
    $(th).parent("li").addClass("active");
}

function formSubmit (url,sTarget){
    document.forms[0].target = sTarget
    document.forms[0].action = url;
    document.forms[0].submit();
    return true;
}