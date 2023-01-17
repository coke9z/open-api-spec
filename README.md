# toranomaki-sample

## OpenAPI

- OpenAPI yamlファイル <br>
  `./toranomaki-sample-api/src/main/resources/api.yaml`

#### ■ インターフェースの自動生成～動作確認 手順

1. コードの自動生成
    - ```$ mvn claen install -f ./toranomaki-sample-api/pom.xml```
2. jarの生成
    - ```$ mvn clean install -f ./toranomaki-sample-impl/pom.xml```
3. Dockerの起動
    - ```$ docker-compose up -d --build```
4. curlで叩いてみる(例)
    - ```$ curl -s -X POST http://localhost:81/sample?sampleQuery=1230 -H "Content-Type: application/json" -H "SAMPLE-HEADER: hoge" -d '{"message":"Hello World!!"}'```
5. Responseの確認(例)
    - ```{"message":"Hello World!!","header":"hoge","query":1230}```

#### ■ yamlファイルからRedoc(API仕様書)の生成

- 前準備 プロジェクトルートで`redoc-cli`をインスコ
  ```$ npm install```

1. htmlファイル生成
    - ```redoc-cli bundle ./toranomaki-sample-api/src/main/resources/api.yaml --output api_reference.html```
2. ブラウザでhtmlファイルを開く

#### ■ 自動テスト 手順

1. TODO テスト用にrefがないAPIを登録取得削除分作る

TODO

- MockServer
- コードからOASの生成

