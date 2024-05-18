<template>
                <el-card>
                    <Calendar class="calendarbox" />
                </el-card>
</template>

<script lang="ts">
// import NavigationBar from './newbar.vue'; // Ensure the import name matches the convention
import Calendar from '@/components/calendar.vue'; // Ensure the import name matches the convention

export default {
    components: {
        // NavigationBar, // Correct naming to match the import
        Calendar
    }
}

</script>

<style scoped>

.main-content {
    z-index: 1001;
}
</style>