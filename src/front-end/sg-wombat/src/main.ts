import { createApp } from 'vue'
import App from './App.vue'
import router from '../router' // 从 src 目录跳出一级到根目录，再进入 router 目录
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios';

const app = createApp(App)


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}


app.use(ElementPlus);


app.use(router);

app.mount('#app');
axios.defaults.baseURL = 'http://192.168.50.209:8080'; // 适当调整为您的后端服务地址
app.config.globalProperties.$axios = axios;
axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('jwt')}`