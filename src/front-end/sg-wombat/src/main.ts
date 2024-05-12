import { createApp } from 'vue'
import App from './App.vue'
import router from '../router' // 确保这里的路径指向您的路由配置文件
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
axios.defaults.baseURL = 'http://localhost:5173'; // 适当调整为您的后端服务地址
app.config.globalProperties.$axios = axios;