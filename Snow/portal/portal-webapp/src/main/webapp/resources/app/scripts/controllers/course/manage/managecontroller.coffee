'use strict'

###*
 # @ngdoc function
 # @name snowApp.controller:CourseManageManagecontrollerCtrl
 # @description
 # # CourseManageManagecontrollerCtrl
 # Controller of the snowApp
###
angular.module('snowApp').controller 'CourseManageManagecontrollerCtrl', ($scope,CourseManageCourseservice) ->
    CourseManageCourseservice.fetchRecentUpgradeCourses().then((data)->
        $scope.recentUpgradeCourses = data
    ,(data)->)

