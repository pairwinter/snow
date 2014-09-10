'use strict'

describe 'Controller: TestcoffeeCtrl', ->

  # load the controller's module
  beforeEach module 'snowApp'

  TestcoffeeCtrl = {}
  scope = {}

  # Initialize the controller and a mock scope
  beforeEach inject ($controller, $rootScope) ->
    scope = $rootScope.$new()
    TestcoffeeCtrl = $controller 'TestcoffeeCtrl', {
      $scope: scope
    }

  it 'should attach a list of awesomeThings to the scope', ->
    expect(scope.awesomeThings.length).toBe 3
