<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>IT-NEWS</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">


    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/styles/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${springMacroRequestContext.contextPath}/styles/font-awesome.min.css">
    <link rel="stylesheet" media="all" href="${springMacroRequestContext.contextPath}/styles/style.css">
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/jquery.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/base/base.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/base/util.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/base/event.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/base/upload.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/component.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/popup.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/popupLogin.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/upload.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/popupUpload.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/util/action.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/site/home.js"></script>
    <script type="text/javascript" src="${springMacroRequestContext.contextPath}/scripts/main/component/popupSendMsg.js"></script>

</head>
<body class="welcome_index">

<header class="navbar navbar-default navbar-static-top bs-docs-nav" id="top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a href="${springMacroRequestContext.contextPath}" class="navbar-brand logo">
                <h1>IT资讯</h1>
                <h3>抢先一步才是王道</h3>
            </a>
        </div>

        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">

            <ul class="nav navbar-nav navbar-right">
                <#if user??>
                <li class="js-share"><a href="javascript:void(0);">分享</a></li>
                <li class=""><a href="${springMacroRequestContext.contextPath}/msg/list">站内信</a></li>
                <li class="top-nav-noti zu-top-nav-li ">
                    <a href="${springMacroRequestContext.contextPath}/user/tosendmsg">   发私信 </a>
                </li>
                <li class=""><a href="${springMacroRequestContext.contextPath}/user/${user.id!}/">${user.name!}</a></li>
                <li class=""><a href="${springMacroRequestContext.contextPath}/logout/">注销</a></li>
                <#else>
                <li class="js-login"><a href="javascript:void(0);">登录</a></li>

                </#if>
            </ul>

        </nav>
    </div>
</header>