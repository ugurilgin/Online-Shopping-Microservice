# Online-Shopping-Microservice

# Teknolojiler
* Java 17
* Lombok
* MongoDb
* Mysql
* Spring Boot
* Spring Cloud  
* REST-API
* API-Gateway
* Eureka-server & Eureka-client
* Spring Sleuth
* Zipkin-server
* Prometheus
* Grafana
* Docker
* Apache Kafka
* Zookeeper

## Kısa kısa..

### Eureka-Server & Eureka-Client
Mikroservislerimize Eureka Client denilebilir. Bu clientlar yml, properties veya java konfigürasyonları ile kendilerine tanımlanmış olan ve 'Eureka Server' denilen ortama kendilerini kaydediyorlar.
Daha sonra bir servise; ihtiyacı olan diğer servis, ulaşmak istediği servisin bilgilerini Eureka Server'dan alarak, işlemine devam ediyor.
Bu sayede port, IP gibi bilgileri bir yerde tutmak zorunda kalmıyoruz.

Elimizde pek çok servis olduğunda bunların port, ip bilgileri, instanceları nelerdir gibi bilgilerinin bir yerde tutulması gerekir. Basitçe bir registry yapısının olması gerekiyor. Daha sonra bunlar birbirlerine erişmek isteyebilirler, bunun için Account service ayağa kalktığında ticket service'in nerde olduğunu yani hangi portta olduğu öğrenmek için eureka'ya gelir. Bir diğeri ise tickettan pek çok instance var; frontend geliştirilirken de eureka üzerinden yük dağılımı (load balancer) için kolaylıklar sağlar. 
Desteklediği algoritma ile default'ta her bir isteği sırasıyla yönlendirir. 

### API-GATEWAY
Mikroservis mimarisi ile ortaya çıkan pek çok servisin yönetiminin, kontrolünü sağlamak için kullanılan yapı.
Eureka üzerinden api'ların dışarıya açılmasını, api gateway ile tek bir nokta haline getirilmesini sağlar. Roller eklenebilir.
### Spring Sleuth
Spring Sleuth, herhangi bir uygulamada birden fazla servisten oluşan bir sistemdeki logları geliştirmek için kullanılan çok güçlü bir araçtır

### Zipkin Server
Tüm mikroservislerdeki requestlerin tracelerini loglayan yapı, örneğin; bir hata odluğunda nerede olduğunu bulmamızı kolaylaştırır.
### Prometheus
Prometheus açık kaynaklı çekme-pull temelli monitör etme-izleme aracıdır.
### Grafana
Grafana Şirketinizin, web sitenizin, bloglarınızın ya da uygulamalarınızın ürettiği, topladığı her türlü veriyi (günlük, ölçüm, izler ve metrikler gibi) sorgulamanıza (query), görselleştirmenize (visualize), uyarı almanıza (alert) imkân sağlayan açık kaynak kodlu pano ve grafik gözlemlenebilirlilik platformudur.

### Docker
Docker kapsayıcısı, bir uygulamanın tüm kodlarını ve bağımlılıklarını standart bir formatta paketleyen bir paketleme formatıdır. Bu paket, uygulamanın farklı bilgi işlem ortamlarında hızlı ve güvenilir bir şekilde çalıştırmasına olanak tanır. Docker kapsayıcısı; kitaplıklar, sistem araçları, kodlar ve çalışma zamanı gibi bir uygulamayı çalıştırmak için gerekli her şeyi içeren popüler, bağımsız ve yürütülebilir bir kapsayıcıdır. Docker, geliştiricilerin kapsayıcı mimarisine alınmış uygulamaları hızla oluşturmasına, test etmesine ve devreye almasına da olanak tanıyan bir yazılım platformudur.

### Apache Kafka
Sadece bir kaynak sisteminiz ve bir hedef sisteminiz olduğunda, bu sistemler arası veri transferi yapmanız gerektiğinde çözüm basittir. Ancak birçok kaynak, birçok hedef sisteminiz olduğunda ve hepsinin birbirleriyle veri transferi yapması gerektiğinde işler gerçekten karmaşık hale gelecek ve herbir sistemin birbiri ile entegrasyonu gerekecektir. Bu entegrasyonlar . protokol seçimi, verilerin nasıl aktarılacağı (TCP, HTTP, FTP, JDBC, REST, SOAP vb), veri formatı, verilerin nasıl parse edileceği (JSON, CSV, XML, Avro, Thrift vb) gibi birçok zorluğu beraberinde getirir. Bunlara ek olarak bir kaynak sisteme yapılan her entegrasyon o sistem üzerinde ek yük yaratacaktır. Apache Kafka bu sorunları aşmak için doğru çözüm olacaktır.
### Zookeeper
ZooKeeper, dağıtık yapıdaki sistemlere koordinasyon hizmeti sağlayan java tabanlı bir yazılımdır.
