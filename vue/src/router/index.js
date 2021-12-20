import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import SignUp from "../views/SignUp.vue";
import Withdraw from "../views/Withdraw.vue";
import GetAllmembers from "../views/GetAllmembers.vue";
import Getmember from "../views/Getmember.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: SignUp,
  },
  {
    path: "/withdraw",
    name: "Withdraw",
    component: Withdraw,
  },
  {
    path: "/admin/users",
    name: "GetAllmembers",
    component: GetAllmembers,
  },
  {
    path: "/admin/users/:uuid",
    name: "Getmember",
    component: Getmember,
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});

export default router;
