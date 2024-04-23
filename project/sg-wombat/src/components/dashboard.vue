<template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">

            <Calendar class="calendarbox" />

        </div>
    </div>
</template>

<style scoped>
.container {
    
    width: 100%;
    display: flex;
    flex-direction: row;
    height: 100vh;
    min-width: 1350px;
    /* border-radius: 30px */
}

.navigation-panel {
    /* position: absolute; */
    top: 0;
    left: 0;
    width: 30%;
    height: 100%;
    z-index: 1000;
}

.dashboard-content {
    /* background-color: green; */
    height: 100vh;

    width: 60%;
   
    display: flex;
    flex-grow: 1;
    z-index: 1001;
    /* border-radius: 30px */
}

.calendarbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: white;
   
    height: 80vh;
}
</style>



<script>
import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import Calendar from './calendar.vue'; // Ensure the import name matches the convention

export default {
    components: {
        NavigationBar, // Correct naming to match the import
        Calendar
    }
}



</script>