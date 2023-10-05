export const environment = {
    production: false,
  
    userConnexion: 'http://localhost:8080/login',
    refreshToken: 'http://localhost:8080/refresh-token',
    logout: '',
    addUser: 'http://localhost:8080/register',
    getUser:'http://localhost:8080/find',
    updateUser:'http://localhost:8080/person/update/',
    avatar: 'http://localhost:8080/person/avatar/',

    addevent: 'http://localhost:8080/event/add',
    getEvent:'http://localhost:8080/event/find',
    addUserEventRole: 'http://localhost:8080/userEventRole/add',
    getUserEvents: 'http://localhost:8080/userEventRole/find',
    deleteEvent: 'http://localhost:8080/event/delete/',

    getUserEventRole:'http://localhost:8080/userEventRole/findRole',
    getUserEventRoleList:'http://localhost:8080/userEventRole/AllByEvent',

    sendingEmail:'http://localhost:8080/event/sendHTMLEmail',
    getEventGuests: 'http://localhost:8080/person/getEventGuests',
    verifyGuest:'http://localhost:8080/person/verifyGuest',
    deleteGuest: 'http://localhost:8080/person/delete/',
    addDetailsToGuest: 'http://localhost:8080/person/addDetailsToGuest/',

    updateModule:'http://localhost:8080/event/update/',

    addTask:'http://localhost:8080/task/add',
    getAllTaskOfEvent: 'http://localhost:8080/task/all/',
    addTaskAssignee: 'http://localhost:8080/task/addTaskAssignee/',
    removeTaskAssignee: 'http://localhost:8080/task/removeTaskAssignee/',
    deleteTask: 'http://localhost:8080/task/deleteTask/',

    fromGuestToUser:'http://localhost:8080/person/guestToUser/',

    uploadPhoto:"http://localhost:8080/photo/upload",
    getPhotos:"http://localhost:8080/photo/findEventPhoto",
    deletePhoto: 'http://localhost:8080/photo/delete/',

    pathImages: 'C:/Users/User/OneDrive/Documents/TimeToToast/TimeToToast_Spring/uploads/',

    getGifts : "http://localhost:8080/gift/getGifts",
    getContributions : "http://localhost:8080/giftContribution/getContribution",
    addContribution : "http://localhost:8080/giftContribution/addContribution",
    giftIsPaid:"http://localhost:8080/gift/giftIsPaid",
    addGift: "http://localhost:8080/gift/addGift",
}
  