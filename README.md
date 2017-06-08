Payara MicroProfile example
==========================

Payara MicroProfileでのプロジェクトExample.

当初はgradleを想定していたが、NetBeansで継続的に開発することを考えるとmavenプロジェクトの方が、maven webappプロジェクトとして扱えて楽だったのでmavenプロジェクトに変更した。

- NetBeansのMavenプロジェクトして開発
    - NetBneasのJavaEEサーバとしてPayaraを登録しておく
    - 動作確認はNetBeansのJavaEEサーバに自動デプロイされる
    - 保存するだけで自動デプロイ->動作確認ができる
- 最終的な成果物はPayara MicroprofileでJavaコマンドで実行することを想定
- DBアクセスにはMyBatisを使用


Quick Start
-------------------

Build

```bash
$ mvn install
```


Create initial database. ($HOME/h2db/test)

```bash
$ bin/initdb.sh
```

Run payara server from war file.

```bash
$ bin/run-payara.sh
```

Request.

```bash
$ curl -i http://localhost:8080/api/emps

{
    "emps": [
        {
          "empno" : 1,
          "ename" : "Scott",
          "hiredate" : "2017/04/01"
        }
    ]
}
```

Make uberjar.

Jar1つで起動可能なサービスの立ち上げ。

```bash
$ java -jar payara-microprofile-*.jar --deploy target/ROOT.war --outputuberjar app.jar
$ java -jar app.jar
...
Payara Micro URLs
...
```

構成
-------

```
 <- HTTP REST -> JAX-RS -- Service -- Dao -- Database

```

### example.api (JAX-RS リソース)

REST APIの提供をJAX-RSで実装。 MyAppはJAX-RSでのApplicationクラスの実装。RESTリソースの定義や各種設定。

EmpResourceはEmpリソースにREST APIを定義。HTTPからの入り口とサービスの呼び出しを記述。
JSONでのリクエスト、レスポンスに対応しドメインロジックを実装するサービスクラスを使って、
リクエストされたリソースを返す。

JSONでのレスポンスのためにJacksonのObjectNodeを返す。MyAppに定義されたJacksonFeatureによってJSONレスポンスが返せるようになっている。

POSTデータはEmpのJavaBeanにマッピングされている。

テストコードは、JerseyTestを使用して、 HTTPの入力から得られるJSONレスポンスを検証する。
ServiceクラスはMockを使用。


### example.service 

ドメインロジックを実装。 EmpResourceから利用されREST,JSON-APIとは分離されて実装される。

本システムがEmp表のCRUDという単純な目的なので、Daoの呼び出しを転送しているだけになっているが、
Daoを駆使してデータベース永続化と分離された形でドメインロジックを実装する役割となる。

テストコードはDaoのMockを使用し、DBは使用しない。
Daoの動作に対してドメインロジックが想定している動作をするか検証する。
Serviceクラスの実装がDaoの呼び出しをしているだけになっているため、テストで検証する内容があまりない。


