# JITSIMEETING环境安装

> 安装prosody
>
> ~~~shell
> sudo apt-get install -y prosody
> 
> #配置文件
> cd /etc/prosody/conf.avail/
> vi ncse.example.org.cfg.lua #有样例配置文件
> 
> #添加软连接
> ln -s /etc/prosody/conf.avail/ncse.example.org.cfg.lua /etc/prosody/conf.d/ncse.example.org.cfg.lua
> 
> #生成证书
> prosodyctl cert generate ncse.example.org
> prosodyctl cert generate auth.ncse.example.org
> 
> #添加信任域名并生成证书
> ln -sf /var/lib/prosody/auth.ncse.example.org.crt /usr/local/share/ca-certificates/ncse.example.org.crt
> update-ca-certificates -f
> 
> #请记住你输入YOURSECRET3
> 
> prosodyctl register focus auth.ncse.example.org YOURSECRET3
> 
> #重新启动prosody XMPP服务器
> prosodyctl restart
> 
> 
> ~~~
>
> ### 安装Nginx
>
> ```
> sudo apt-get install nginx
> ```

配置文件：

~~~shell
cd /etc/nginx/sites-avaiable
vi nsce.example.org
#添加内容
server {
    listen 0.0.0.0:443 ssl http2;
    listen [::]:443 ssl http2;
    # tls configuration that is not covered in this guide
    # we recommend the use of https://certbot.eff.org/
    server_name ncse.example.com;
    # set the root
    root /srv/ncse-meet;
    index index.html;
    location ~ ^/([a-zA-Z0-9=\?]+)$ {
        rewrite ^/(.*)$ / break;
    }
    location / {
        ssi on;
    }
    # BOSH, Bidirectional-streams Over Synchronous HTTP
    # https://en.wikipedia.org/wiki/BOSH_(protocol)
    location /http-bind {
        proxy_pass      http://localhost:5280/http-bind;
        proxy_set_header X-Forwarded-For $remote_addr;
        proxy_set_header Host $http_host;
    }
    #根据前端部署配置
    location /external_api.js {
        alias /srv/ncse-meet/libs/external_api.min.js;
    }
}
~~~

添加软链

~~~shell
cd /etc/nginx/sites-enabled
ln -s ../sites-available/ncse.example.com ncse.example.com
~~~

