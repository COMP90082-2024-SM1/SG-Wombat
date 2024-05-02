<template>
    <div class="background">
        <div class="bigcontainer">
            <div class="science">
                Science Gallery
            </div>
            <div class="content">
                <a class="innerbox" @click="gotodashboard('a1', 'dashboardPage')">

                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a1' }">
                            <HomeFilled />
                        </div>
                    </div>

                    <div class="text" :class="{ 'new-text': activeItem === 'a1' }">
                        Dashboard
                    </div>
                </a>

                <a class="innerbox" @click="activateAndNavigate('a2', 'bookingpage')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a2' }">
                            <List />
                        </div>
                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a2' }">
                        Booking
                    </div>
                </a>
                <a class="innerbox" @click="activateAndNavigate('a3', 'programpage')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a3' }">
                            <Opportunity />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a3' }">
                        Programs
                    </div>
                </a>
                <a class="innerbox" @click="activateAndNavigate('a4', 'roompage')">
                    <div class="iconbox">
                        <div class="icon" :class="{ 'new-text': activeItem === 'a4' }">
                            <Place />
                        </div>

                    </div>
                    <div class="text" :class="{ 'new-text': activeItem === 'a4' }">
                        Room Information
                    </div>
                </a>
                <a class="innerbox" @click="activateAndNavigate('a3', 'schoolpage')">
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
import { useRouter } from 'vue-router';

const activeItem = ref('a1');  // Assuming 'a1' is your default active item
const router = useRouter();

function activateAndNavigate(item, routeName) {
    // Update the active item only if it's different
    if (activeItem.value !== item) {
        activeItem.value = item;
    }
    // Navigate to the route
    router.push({ name: routeName });
}
</script>


<style scoped>
@import '../assets/navigation_bar.css';
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
</style>
