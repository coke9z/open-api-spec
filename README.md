# toranomaki-sample

## OpenAPI

### ディレクトリ構成
```
open-api-spec
  ├ docker/                    ... Docker設定
  ├ docker-compose.yaml        ... Docker設定
  │
  ├ toranomaki-sample-api/     ... OpenAPI
  ├ toranomaki-sample-impl/    ... OpenAPIから自動生成したInterfaceを用いたAPI実装
  │
  └ openapi-to-postmanv2/      ... OpenAPIからPostmanを用いたテスト
```

### インターフェースの自動生成～動作確認 手順

1. コードの自動生成<br>
  ```$ mvn claen install -f ./toranomaki-sample-api/pom.xml```
2. jarの生成<br>
  ```$ mvn clean install -f ./toranomaki-sample-impl/pom.xml```
3. Dockerの起動<br>
  ```$ docker-compose up -d --build```
4. curlで叩いてみる(例)<br>
  ```$ curl -s -X POST http://localhost:81/sample?sampleQuery=1230 -H "Content-Type: application/json" -H "SAMPLE-HEADER: hoge" -d '{"message":"Hello World!!"}'```
5. Responseの確認(例)<br>
  ```{"message":"Hello World!!","header":"hoge","query":1230}```

### yamlファイルからRedoc(API仕様書)の生成

前準備 プロジェクトルートで`redoc-cli`をインスコ<br>
  ```$ npm install```

1. htmlファイル生成<br>
   ```redoc-cli bundle ./toranomaki-sample-api/src/main/resources/api.yaml --output api_reference.html```
2. ブラウザでhtmlファイルを開く

### 自動テスト 手順

前準備 `./openapi-to-postmanv2/`に移動してテストに必要なpackageをインスコ<br>
  ```
  $ cd ./openapi-to-postmanv2/
  $ npm install
  ```

- `index.js`を実行してテスト<br>
  ```$ npm start```
- `index.js`内でやっている内容
  1. OpenAPI(yamlファイル)の読み込み
  2. PostmanのCollections Jsonファイルに変換
  3. JsonファイルのRequestをテスト用に書き換え
  4. テスト実行 (TODO)


## TODO

- MockServer
  - OASではなく対応予定
- コードからOASの生成
  - あまり意味なさそうなので要検討

