### Get

GET请求的数据会附在URL之后（就是把数据放置在HTTP协议头中），以?分割URL和传输数据，参数之间以&相连，如：http://api.juheapi.com/japi/toh?key=eff36bdaeeb868a6b8057a34f32d1326&v=1.0&month=11&day=1。

GET请求发送的参数如果数据是英文字母或数字，则按原样发送，如果是空格，则转换为+，如果是中文或其他字符，则直接把字符串用BASE64加密，得出如 %E4%BD%A0%E5%A5%BD 这类似的字符串，其中％XX中的XX为该符号以16进制表示的ASCII。

### Post

POST请求的参数不是放在URL字符串里面，而是放在HTTP请求的正文内，请求的参数被封装起来以流的形式发送给服务端。

### Get or Post?

对于GET方式提交数据的大小，HTTP协议并没有硬性限制，但某些浏览器及服务器会对它进行限制，如IE对URL长度的限制是2083字节(2K+35)。理论上POST也没有限制，可传较大量的数据。

POST的安全性要比GET的安全性高。比如：通过GET提交数据，用户名和密码将明文出现在URL上，因为登录页面有可能被浏览器缓存，如果其他人查看浏览器的历史纪录，那么别人就可以拿到你的账号和密码了，除此之外，使用GET提交数据还可能会造成Cross-site request forgery（CSRF，跨站请求伪造）攻击。

一般来说，Get是向服务器索取数据的一种请求，而Post是向服务器提交数据的一种请求。

### HttpURLConnection

* HttpURLConnection对象不能直接构造，需要通过URL类中的openConnection()方法来获得。

* HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的TCP连接，并没有实际发送HTTP请求。HTTP请求实际上直到我们获取服务器响应数据（如调用getInputStream()、getResponseCode()等方法）时才正式发送出去。

* 对HttpURLConnection对象的配置都需要在connect()方法执行之前完成。

* HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。

* HTTP正文的内容是通过OutputStream流写入的， 向流中写入的数据不会立即发送到网络，而是存在于内存缓冲区中，待流关闭时，根据写入的内容生成HTTP正文。

* 调用getInputStream()方法时，返回一个输入流，用于从中读取服务器对于HTTP请求的返回信息。

* 我们可以使用HttpURLConnection.connect()方法手动的发送一个HTTP请求，但是如果要获取HTTP响应的时候，请求就会自动的发起，比如我们使用HttpURLConnection.getInputStream()方法的时候，所以完全没有必要调用connect()方法。

### HttpClient

解决Android studio开发找不到HttpClient问题

在Android 6.0（API 23） 中，Google已经移除了Apache HttpClient 想关类，推荐使用HttpUrlConnection，如果要继续使用，在Android studio对应的module下的build.gradle文件中加入：
android {
useLibrary 'org.apache.http.legacy'
}

1.创建HttpClient对象。

2.如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。

3.如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HttpParams params)方法来添加请求参数；对于HttpPost对象而言，也可调用setEntity(HttpEntity entity)方法来设置请求参数。

4.调用HttpClient对象的execute(HttpUriRequest request)发送请求，执行该方法返回一个HttpResponse。

5.调用HttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。

### OKHttpClient

一般的get请求
一般的post请求
基于Http的文件上传
文件下载
加载图片
支持请求回调，直接返回对象、对象集合
支持session的保持


