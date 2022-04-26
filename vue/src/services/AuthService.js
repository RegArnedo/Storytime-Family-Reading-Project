import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  registerFamilyUser(user, userId) {
    return axios.post(`/parent/dashboard/${userId}`, user)
  },

  getFamilyByUserId(userId){
    return axios.get(`/parent/dashboard/${userId}`)
  },

  getUserById(userId) {
    return axios.get(`/user/${userId}`); 
  },

  getDashboardInfo(familyId) {
    return axios.get(`/user/dashboard/${familyId}`)
  }
}
