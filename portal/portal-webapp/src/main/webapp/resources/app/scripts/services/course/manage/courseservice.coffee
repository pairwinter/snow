'use strict'

###*
 # @ngdoc service
 # @name snowApp.course/manage/courseservice
 # @description
 # # course/manage/courseservice
 # Service in the snowApp.
###
angular.module('snowApp').service 'CourseManageCourseservice', ($http,$q,i18n) ->
    urls = [
        '/course/fetchCourses'
        '/course/fetchCharpters'
        '/course/fetchSections'
    ]
    buildFunction = (url)->
        methodName = url.split('/')[2]
        this[methodName] = ()->
            deferred = $q.defer()
            $http({
                method:'GET'
                url:url
            }).success((data)->
                deferred.resolve(data)
            ).error(->
                deferred.reject(i18n['error_can_not_receive_data'])
            )
    buildFunction.call this,url for url in urls

    # AngularJS will instantiate a singleton by calling "new" on this function
