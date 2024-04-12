<template>
    <div class="background">
        <div class="bigcontainer">
            <div class="science">
                Science Gallery
            </div>
            <div class="content">
                <a class="innerbox" @click="activate('a1'); goToDashboard">

                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a1' }">
                            <HomeFilled />
                        </div>
                    </div>

                    <div class="text" :class="{ 'new-text': activeItem === 'a1' }">
                        Dashboard
                    </div>
                </a>
                <a class="innerbox" @click="activate('a2'); goTobooking">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a2' }">
                            <List />
                        </div>
                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a2' }">
                        Booking
                    </div>
                </a>
                <a class="innerbox" @click="activate('a3')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a3' }">
                            <Opportunity />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a3' }">
                        Programs
                    </div>
                </a>
                <a class="innerbox" @click="activate('a4')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a4' }">
                            <Place />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a4' }">
                        Room Information
                    </div>
                </a>
                <a class="innerbox" @click="activate('a5')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a5' }">
                            <School />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a5' }">
                        Schools
                    </div>
                </a>
                <a class="lastbox" @click="activate('a6')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a6' }">
                            <Share />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a6' }">
                        Export Report
                    </div>
                </a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'
const activeItem = ref('a1');
const router = useRouter()

function activate(item) {
    if (activeItem.value !== item) {
        activeItem.value = item;
    }
}

function goToDashboard() {
    if (router.hasRoute('DashboardPage')) {
        router.push({ name: 'DashboardPage' });
    } else {
        console.error('Route "DashboardPage" does not exist!');
    }
}

function goTobooking() {
    if (router.hasRoute('bookingpage')) {
        router.push({ name: 'bookingpage' });
    } else {
        console.error('Route "bookingpage" does not exist!');
    }
}

</script>

<style scoped>
@import '../assets/navigation_bar.css';
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
</style>
