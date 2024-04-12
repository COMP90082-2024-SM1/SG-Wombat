import { createRouter, createWebHistory } from 'vue-router';
import Login from '../src/components/login.vue';
import DashboardPage from '../src/components/dashboard.vue';
import bookingpage from '../src/components/bookingpage.vue';
import roompage from '../src/components/roompage.vue';
import schoolpage from '../src/components/schoolpage.vue';
import programpage from '../src/components/programpage.vue';

const routes = [
    { path: '/', component: Login },
    {
        path: '/dashboard',
        name: 'DashboardPage',
        component: DashboardPage
    },
    {
        path: '/booking',
        name: 'bookingpage',
        component: bookingpage
    },
    {
        path: '/room',
        name: 'roompage',
        component: roompage
    },
    {
        path: '/school',
        name: 'schoolpage',
        component: schoolpage
    },
    {
        path: '/program',
        name: 'programpage',
        component: programpage
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
