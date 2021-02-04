# 编译jicofo

~~~shell
mvn package -DskipTests -Dassembly.skipAssembly=false -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
~~~

JDK导入证书：

~~~shell
keytool -import -file /var/lib/prosody/auth.jitsi.example.org.crt -storepass changeit -keystore /usr/local/jdk8/jre/lib/security/cacerts -alias jitsi
~~~

Run jicofo

~~~shell
unzip target/jicofo-1.1-SNAPSHOT-archive.zip
cd jicofo-1.1-SNAPSHOT-archive'
 cd ~/jitsi/jicofo-1.1-SNAPSHOT/ && nohup ./jicofo.sh --host=localhost --domain=jitsi.example.org --secret=kod93jd --user_domain=auth.jitsi.example.org --user_name=focus --user_password=gztech > ~/jicofo.log
~~~

下载依赖问题解决：

~~~shell
sudo vi /etc/hosts
#添加如下
52.74.223.119 github.com
192.30.253.119 gist.github.com
54.169.195.247 api.github.com
185.199.111.153 assets-cdn.github.com
151.101.108.133 user-images.githubusercontent.com
151.101.76.133 gist.githubusercontent.com
151.101.76.133 cloud.githubusercontent.com
151.101.76.133 camo.githubusercontent.com
151.101.76.133 avatars0.githubusercontent.com
151.101.76.133 avatars1.githubusercontent.com
151.101.76.133 avatars2.githubusercontent.com
151.101.76.133 avatars3.githubusercontent.com
151.101.76.133 avatars4.githubusercontent.com
151.101.76.133 avatars5.githubusercontent.com
151.101.76.133 avatars6.githubusercontent.com
151.101.76.133 avatars7.githubusercontent.com
151.101.76.133 avatars8.githubusercontent.com
199.232.96.133 raw.githubusercontent.com
~~~



