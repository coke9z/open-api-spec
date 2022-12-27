# toranomaki-sample

## OpenAPI
- OpenAPI yamlファイル <br>
 `./toranomaki-sample-api/src/main/resources/api.yaml`

#### 動作確認 手順
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



TODO
- 自動テスト
- MockServer
- コードからOASの生成
