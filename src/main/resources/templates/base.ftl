<#macro page_head>
    <title>Base</title>
</#macro>

<#macro page_body>
</#macro>

<#macro display_page>
    <!doctype html>
    <html>
    <head>
        <@page_head/>
        <link rel="stylesheet" href="/static/css/style.css">
    </head>
    <body>
        <@page_body/>
    </body>
    </html>
</#macro>