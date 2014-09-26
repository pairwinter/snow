'use strict'

###*
 # @ngdoc function
 # @name snowApp.controller:CourseManageListcoursesCtrl
 # @description
 # # CourseManageListcoursesCtrl
 # Controller of the snowApp
###
angular.module('snowApp').controller 'CourseManageListCoursesCtrl', ($scope) ->
    gridData = ({id:"id"+person,name:"name"+person,age:person} for person in [1..500])
    $scope.pageData = []
    $scope.pagingOptions = {
        pageSizes:[5,50,100,150,200,250,500,1000]
        pageSize:250
        currentPage:1
    }
    $scope.setPagingData = (data,page,pageSize)->
        pagedData = data.slice (page-1)*pageSize, page*pageSize
        $scope.pagingOptions.currentPage = page
        $scope.totalServerItems = data.length
        $scope.pageData = pagedData
        $scope.$apply() if !$scope.$$phase

    $scope.setPagingData(gridData,$scope.pagingOptions.currentPage,$scope.pagingOptions.pageSize)

    $scope.gridOptions = {
        data:"pageData"
        enablePaging:true
        totalServerItems:'totalServerItems'
        pagingOptions:$scope.pagingOptions
        showFooter:true
        showSelectionCheckbox:true
        enablePinning: true
        columnDefs:[
            {field:"id",width:150}
            {field:"name",width:150}
            {field:"age",width:100}
        ]
    }

    $scope.$watch('pagingOptions',(newVal,oldVal)->
        if (newVal != oldVal and newVal.currentPage != oldVal.currentPage)
            $scope.setPagingData(gridData,newVal.currentPage,newVal.pageSize)
        else if(newVal.pageSize != oldVal.pageSize)
            $scope.setPagingData(gridData,1,newVal.pageSize)
    ,true)
    ""
