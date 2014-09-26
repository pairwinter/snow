'use strict';

###
 * @ngdoc overview
 * @name snowApp
 * @description
 * # snowApp
 *
 * Main module of the application.
###
angular.module('snowApp', [
    'ngAnimate'
    'ngCookies'
    'ngResource'
    'ngRoute'
    'ngSanitize'
    'ngTouch'
    'ngGrid'
    'i18n'
]).config(($routeProvider,i18nProvider)->
    $routeProvider.when('/', {
        templateUrl: i18nProvider.parseUrl('/views/course/manage/listcourses.html'),
        controller: 'CourseManageListCoursesCtrl'
    }).otherwise({
        redirectTo: '/'
    })
);
