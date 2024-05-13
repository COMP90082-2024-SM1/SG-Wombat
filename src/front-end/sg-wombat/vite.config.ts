import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import { fileURLToPath, URL } from 'node:url'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    }
  },
  optimizeDeps: {
    include: ['element-plus/dist/index.css']
  },
  server: {
    port: 8080, // 运行前端的端口
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // 要代理的目标地址
        changeOrigin: true,              // 允许跨域
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
});
