<template>
  <div class="background">
    <div class="bigcontainer">
      <div class="innercontainer">
        <div class="science">
          Science Gallery
        </div>
        <div class="admin">
          Admin Login
        </div>
        <div class="smallcontainer">
          <div class="username">
            Username:
          </div>
          <div class="usernameinput">
            <input type="text" v-model="loginForm.username">
          </div>
          <div class="password">
            Password:
          </div>
          <div class="passwordinput">
            <input type="password" v-model="loginForm.password">
          </div>
          <div class="forgetpass">
            Forget Password?
          </div>
        </div>
        <div class="buttoncontainer">
          <!-- <button @click="login"> -->
            <button @click="goToTargetPage">
            Log In
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
const router = useRouter();
function goToTargetPage() {
    router.push({ name: 'dashboardPage' });
}
const loginForm = reactive({
  username: '',
  password: ''
});

// const login = async () => {
//   try {
//       const response = await axios.post('/login', loginForm);
//       console.log(JSON.stringify(response))
//       console.log(response?.data.msg)
//       if (response?.data.msg==='success') {
//           console.log('Logged in successfully, JWT:', response.data.data);
//           localStorage.setItem('jwt', response.data.data);  // 保存 JWT 到 localStorage
//           router.push({ name: 'dashboardPage' });
//       } else {
//           alert('Login failed: ' + response.data.message);
//       }
//   } catch (error) {
//       console.error('Login error:', error);
//       alert('Login failed, please check the console for more information.');
//   }
// };

</script>

<style scoped>
@import '../assets/login.css';
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
</style>  