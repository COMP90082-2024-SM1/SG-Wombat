import axios from 'axios';

const http = axios.create({
  baseURL: 'https://api.yourdomain.com', // 更改为你的后端API的基础URL
  headers: {
    'Content-Type': 'application/json'
  }
});

export default http;