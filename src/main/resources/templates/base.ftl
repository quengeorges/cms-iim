<#macro page_head>
    <title>Base</title>
</#macro>

<#macro page_body>
</#macro>

<#macro navbar>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <#if connect>
                    <a class="btn btn-primary col col-3 align-self-end" href="/logout">Logout</a>
                <#else>
                    <a class="btn btn-primary col col-3 align-self-end" href="/login">Login</a>
                </#if>
            </div>
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
        <div class="px-2">
            <@page_body/>
        </div>
    </body>
    </html>
</#macro>