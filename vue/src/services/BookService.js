import axios from 'axios';

export default {
    
    getBooks(userId){
        return axios.get(`/bookshelf/${userId}`)
    },

    getAllBooks(){
        return axios.get('/bookshelf')
    },

    getAllBooksByFamily(familyId){
        return axios.get(`/readinglog/books/${familyId}`)
    },

    addBook(userId, book) {
        return axios.post(`/bookshelf/${userId}`, book)
    },


// Reg Note:  Start of implementation of ReadingLog.vue code.  

   logReading(userBook) {
        return axios.post('/readinglog', userBook)
    },

    getReadingLog(userId) {
        return axios.get(`/readinglog/${userId}`)
    },

// For UserModal - check w/ Colin to see if he can switch 'id' for 'userId' for
// consistency
    getUserDetail(id) {
        return axios.get(`/user/dashboard/detail/${id}`)
    }



}