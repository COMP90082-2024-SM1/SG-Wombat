import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/login.vue';
import dashboardPage from '@/views/dashboardView.vue';
// import bookingpage from '../src/components/bookingpage.vue';
import bookingpage from '@/views/bookingView.vue'
// import roompage from '../src/components/roompage.vue';
import schoolpage from '@/components/schoolpage.vue';
// import programpage from '../src/components/programpage.vue';
import newbar from '@/components/newbar.vue';
import teacherpage from '@/components/teacherpage.vue';
import teacherpage2 from '@/components/teacherpage2.vue';
import layout from '@/components/layout.vue';
// import programPage from '../src/components/programPageNew.vue';
import programPage from '@/views/programView.vue';


const routes = [
    { path: '/', component: Login },
    {
        path: '/',
        name: 'layout',
        redirect: '/dashboard',
        component: layout,
        children: [
            {
                path: '/program',
                name: 'programpage',
                component: programPage
            },
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

        ]
    },

    // {
    //     path: '/dashboard',
    //     name: 'dashboardPage',
    //     component: dashboardPage
    // },
    // {
    //     path: '/booking',
    //     name: 'bookingpage',
    //     component: bookingpage
    // },
    {
        path: '/school',
        name: 'schoolpage',
        component: schoolpage
    },
    // {
    //     path: '/program',
    //     name: 'programpage',
    //     component: programPage
    // },

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
    // {
    //     path: '/layout',
    //     name: 'layout',
    //     component: layout
    // },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
