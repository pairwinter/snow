// Generated by CoffeeScript 1.7.1
(function() {
  'use strict';

  /**
    * @ngdoc function
    * @name snowApp.controller:CourseManageListcoursesCtrl
    * @description
    * # CourseManageListcoursesCtrl
    * Controller of the snowApp
   */
  angular.module('snowApp').controller('CourseManageListCoursesCtrl', function($scope) {
    var gridData, person;
    gridData = (function() {
      var _i, _results;
      _results = [];
      for (person = _i = 1; _i <= 500; person = ++_i) {
        _results.push({
          id: "id" + person,
          name: "name" + person,
          age: person
        });
      }
      return _results;
    })();
    $scope.pageData = [];
    $scope.selectedItems = [];
    $scope.pagingOptions = {
      pageSizes: [5, 50, 100, 150, 200, 250, 500, 1000],
      pageSize: 250,
      currentPage: 1
    };
    $scope.setPagingData = function(data, page, pageSize) {
      var pagedData;
      pagedData = data.slice((page - 1) * pageSize, page * pageSize);
      $scope.pagingOptions.currentPage = page;
      $scope.totalServerItems = data.length;
      $scope.pageData = pagedData;
      if (!$scope.$$phase) {
        return $scope.$apply();
      }
    };
    $scope.setPagingData(gridData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
    $scope.gridOptions = {
      data: "pageData",
      enablePaging: true,
      totalServerItems: 'totalServerItems',
      pagingOptions: $scope.pagingOptions,
      showFooter: true,
      showSelectionCheckbox: true,
      enablePinning: true,
      columnDefs: [
        {
          field: "id",
          width: 150
        }, {
          field: "name",
          width: 150
        }, {
          field: "age",
          width: 100
        }
      ],
      selectedItems: $scope.selectedItems
    };
    $scope.$watch('pagingOptions', function(newVal, oldVal) {
      if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        return $scope.setPagingData(gridData, newVal.currentPage, newVal.pageSize);
      } else if (newVal.pageSize !== oldVal.pageSize) {
        return $scope.setPagingData(gridData, 1, newVal.pageSize);
      }
    }, true);
    $scope.$watch('selectedItems', function(newSelectedItems, oldSelectedItems) {
      console.log(newSelectedItems);
      return console.log(oldSelectedItems);
    }, true);
    return "";
  });

}).call(this);

//# sourceMappingURL=listcourses.map
