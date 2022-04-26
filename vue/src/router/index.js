import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/HomeView.vue'
import Login from '../views/LoginView.vue'
import Logout from '../views/LogoutView.vue'
import Register from '../views/RegisterView.vue'
import store from '../store/index'
import ParentDashboard from '../views/ParentDashboardView.vue'
import ParentChildDash from '../views/ParentChildDashView.vue'
// import RegisterChildView from '../views/RegisterChildView.vue'
import BookShelf from '../views/BookshelfView.vue'
import HistoryView from '../views/HistoryView'
// import ReadingLog from '../components/ReadingLog'



Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {//reg for first parent of family to use
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    // {//are we doing separate child and adult registrations still? 
    //   path: "/register/child",
    //   name: "register-child",
    //   component: RegisterChildView,
    //   meta: {
    //     requiresAuth: false
    //   }
    // },
    { // Router for ParentDashboard - MVP for Sprint 1
      path: "/parent/dashboard/:userId",
      name: "parent",
      component: ParentDashboard,
      meta: {
        requiresAuth: false
      }
    },
    { // Router for ParentDashboard - MVP for Sprint 1
      path: "/dashboard/:userId",
      name: "dashboard",
      component: ParentDashboard,
      // beforeEnter: (to,from,next) => {
      //   if (this.$store.state.user.authorities[0].name == 'ROLE_PARENT') {
      //     next()
      //   }
      // },
      meta: {
        requiresAuth: false
      }
    },

    {//where prizes will be located
      path: "/parent/child-dashboard/:userId",
      name: "parent-child-dash",
      component: ParentChildDash,
      meta: {
        requiresAuth: false
      }
    },
    {//add user id. changed just for presentation tomorrow. using as "add and view books page" combined?!
      path: '/bookshelf/:userId',
      name: 'bookshelf',
      component: BookShelf,
    },
    {
      // Working on hook for new version of BookshelfView
      path: "/history",
      name: "reading-history",
      component: HistoryView,
      meta: {
        requiresAuth: false
      }
    },
    // {
    //   path: "/readinglog/:userId",
    //   name: "reading-log",
    //   component: ReadingLog,
    //   meta: {
    //     requiresAuth: false
    //   }

    // }  
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
