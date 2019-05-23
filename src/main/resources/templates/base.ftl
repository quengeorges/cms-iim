<#macro page_head>
    <title>Base</title>
</#macro>

<#macro page_body>
</#macro>

<#macro navbar>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="/login">Login</a>
        </div>
    </nav>
</#macro>

<#macro display_page>
    <!doctype html>
    <html>
    <head>
        <@page_head/>
        <link rel="stylesheet" href="/static/css/reset.css">
<#--        <link rel="stylesheet" href="/static/css/style.css">-->
        <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    </head>
    <body>
        <@navbar/>
        <@page_body/>
    </body>
    </html>
</#macro>