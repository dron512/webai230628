// var a = 10;
// var a = "10";

const express = require('express');
const cors = require('cors');
const app = express();
const port = 3000;

// http://localhost:3000/login?a=10&b=20
// 들어오는 파라메타 처리
app.use(express.urlencoded({ extended: true }));
// fetch 써서 들어오는거 json 함수로 처리
app.use(express.json());
app.use(cors());

app.post('/login', (req, res) => {
  console.log(req.body);
  res.send('Hello world');
});

app.listen(port, () => {
  console.log('잘실행됨');
});
