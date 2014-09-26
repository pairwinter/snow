'use strict'

describe 'Controller: CourseManageListcoursesCtrl', ->

  # load the controller's module
  beforeEach module 'snowApp'

  CourseManageListcoursesCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CourseManageListcoursesCtrl = $controller 'CourseManageListcoursesCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
