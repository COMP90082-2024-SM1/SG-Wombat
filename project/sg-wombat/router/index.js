import { createRouter, createWebHistory } from 'vue-router';
import Login from '../src/components/login.vue';
import dashboardPage from '../src/components/dashboard.vue';
import bookingpage from '../src/components/bookingpage.vue';
import roompage from '../src/components/roompage.vue';
import schoolpage from '../src/components/schoolpage.vue';
import programpage from '../src/components/programpage.vue';
import newbar from '../src/components/newbar.vue';
import teacherpage from '../src/components/teacherpage.vue';
import teacherpage2 from '../src/components/teacherpage2.vue';
import layout from '../src/components/layout.vue';



const routes = [
    { path: '/', component: Login },
    {
        path: '/dashboard',
        name: 'dashboardPage',
        component: dashboardPage
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

    {
        path: '/newbar',
        name: 'newbar',
        component: newbar
    },
    {
        path: '/teacherpage',
        name: 'teacherpage',
        component: teacherpage
    },
    {
        path: '/teacherpage2',
        name: 'teacherpage2',
        component: teacherpage2
    },
    {
        path: '/layout',
        name: 'layout',
        component: layout
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
