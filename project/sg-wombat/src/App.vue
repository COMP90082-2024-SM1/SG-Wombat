<script setup>
import login from './components/login.vue'
import navigation_bar from './components/navigationbar.vue'
import dashboardpage from './components/dashboard.vue'
import bookingpage from './components/bookingpage.vue';
import roompage from './components/roompage.vue';
import schoolpage from './components/schoolpage.vue';
import programpage from './components/programpage.vue';

</script>

<template>
  <div id="app">
    <router-view></router-view>
  </div>

</template>