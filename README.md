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


