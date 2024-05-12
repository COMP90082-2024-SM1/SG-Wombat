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
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();
const loginForm = ref({
    username: '',
    password: ''
});

async function login() {
    try {
        const response = await axios.post('/api/login', loginForm.value);
        if (response.data.success) {
            router.push({ name: 'dashboardPage' });
        } else {
            alert('Login failed: ' + response.data.message);
        }
    } catch (error) {
        alert('Error logging in: ' + error.message);
    }
}
</script>

<style scoped>
@import '../assets/login.css';
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
</style>
