export const environment = {
    production: false,
  
    userConnexion: 'http://localhost:8080/login',
    refreshToken: 'http://localhost:8080/login/refresh-token',
    logout: '',
    addUser: 'http://localhost:8080/register',
    getUser:'http://localhost:8080/find',
    updateUser:'http://localhost:8080/user/update/',

    addevent: 'http://localhost:8080/event/add',
    getEvent:'http://localhost:8080/event/find',
    addUserEventRole: 'http://localhost:8080/userEventRole/add',
    getUserEvents: 'http://localhost:8080/userEventRole/find',

    getUserEventRole:'http://localhost:8080/userEventRole/findRole',
    getUserEventRoleList:'http://localhost:8080/userEventRole/AllByEvent',
}
  