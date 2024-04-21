<template>
    <div class="container">
        <NavigationBar class="navigation-panel" />
        <div class="dashboard-content">
            <Programtable class="listbox" />
        </div>
    </div>
</template>

<style scoped>
.container {
    position: relative;
    width: 100%;
    display: flex;
    flex-direction: row;
    min-height: 1080px;
    border-radius: 30px;
}

.navigation-panel {
    position: absolute;
    top: 0;
    left: 0;
    width: 30%;
    height: 100%;
    z-index: 1000;
}

.dashboard-content {
    background-color: white;
    height: 1080px;
    width: 920px;
    width: 70%;
    margin-left: 400px;
    display: flex;
    flex-grow: 1;
    z-index: 1001;
    border-radius: 30px;
}

.listbox {
    margin-top: 50px;
    margin-left: 30px;
    background-color: white;
    width: 1000px;
    height: 900px;
}


</style>



<script>
import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import Programtable from './programtable.vue';

export default {
    components: {
        NavigationBar, // Correct naming to match the import
        Programtable,
    }
}


</script>