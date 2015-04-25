'use strict'

describe 'Controller: CourseManageManagecontrollerCtrl', ->

  # load the controller's module
  beforeEach module 'snowApp'

  CourseManageManagecontrollerCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    CourseManageManagecontrollerCtrl = $controller 'CourseManageManagecontrollerCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
