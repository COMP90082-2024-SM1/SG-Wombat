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
                        <input type="text">
                    </div>
                    <div class="password">
                        Password:
                    </div>
                    <div class="passwordinput">
                        <input type="password">
                    </div>
                    <div class="forgetpass">
                        Forget Password?
                    </div>
                </div>
                <div class="buttoncontainer">
                    <button @click="goToTargetPage">
                        Log In
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function goToTargetPage() {
    if (router.hasRoute('DashboardPage')) {
        router.push({ name: 'DashboardPage' });
    } else {
        console.error('Route "DashboardPage" does not exist!');
    }
}

</script>


<style scoped>
@import '../assets/login.css';
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
</style>