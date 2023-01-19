const fs = require('fs');
const Collection = require('postman-collection').Collection;

// 必要に応じてPathは変更
const yamlPath = '../toranomaki-sample-api/src/main/resources/api.yaml';
const readFileData = fs.readFileSync(yamlPath, {encoding: 'UTF8'});

const updateRequestQuery = (request, key, value) => {
  const findQueryParam = request.url.query.find(
      queryParam => queryParam.key === key);
  findQueryParam.update({
    key: key,
    value: value
  });
}

const updateRequestHeader = (request, key, value) => {
  const findHeaderParam = request.headers.find(
      headerParam => headerParam.key === key);
  findHeaderParam.update({
    key: key,
    value: value
  });
}

const updateRequestBody = (request, body) => {
  request.body.update(body);
}

const updateRequest = (request) => {

  // サンプルAPI
  if (request.url.getPath().includes('sample')) {
    updateRequestQuery(request, 'value', 'hogehoge');
    updateRequestHeader(request, 'SAMPLE-HEADER', 'fugafuga');
    updateRequestBody(request,
        fs.readFileSync('./test_file/sample_api_request.json',
            {encoding: 'UTF8'}));
  }

  return request;
}

const updateItem = (item) => {

  if (item.items) {
    item.items.all().forEach(item => updateItem(item));
  } else {
    // 追加
    item.events.add({
      listen: 'test',
      script: {
        exec: "pm.test('response 200 test', () => {\n"
            + "    pm.response.to.have.status(200);\n"
            + "});"
      },
      type: 'text/javascript'
    });

    item.request = updateRequest(item.request);
  }

  return item;
}

// yamlファイルを読み込んでPostman CollectionsのJSONファイルを生成する
// const Index = require('openapi-to-postmanv2');
const Index = require('openapi-to-postmanv2');
const newman = require("newman");
Index.convert({type: 'string', data: readFileData}, {},
    (err, conversionResult) => {
      if (!conversionResult.result) {
        console.error('変換エラー', conversionResult.reason);
        return;
      }
      console.log("1. yaml読み込み");
      const convertData = conversionResult.output[0].data;
      const collection = new Collection(convertData);

      // Requestの書き換え
      console.log("2. Request書き換え");
      collection.items.all().forEach(item => updateItem(item))

      // Postman CollectionsのJSONファイルを生成する
      console.log("3. JSONファイル生成")
      fs.writeFile('./output_json/postman-collections.json',
          JSON.stringify(collection, null, 4), (err) => {
            if (err) {
              console.error(err)
            }
          });

      console.log("4. テスト");
      newman.run({
        collection: collection,
        reporters: 'cli',
        environment: require('./test_file/local.postman_environment.json')
      }, (err) => {
        if (err) {
          console.error(err);
        }
      });

    }
);
