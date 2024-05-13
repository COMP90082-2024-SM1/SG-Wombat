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
            <button @click="login">
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
  
  const loginForm = reactive({
    username: '',
    password: ''
  });
  
  const login = async () => {
    try {
      const response = await axios.post('/api/login', loginForm);
      if (response.data && response.data.success) {
        console.log('Logged in successfully, JWT:', response.data.data);
        // Redirect or save the JWT in local storage or cookies
        // router.push({ name: 'dashboardPage' });
      } else {
        alert('Login failed: ' + response.data.message);
      }
    } catch (error) {
      console.error('Login error:', error);
      alert('Login failed, please check the console for more information.');
    }
  };
  </script>
  
  <style scoped>
  @import '../assets/login.css';
  @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
  </style>  