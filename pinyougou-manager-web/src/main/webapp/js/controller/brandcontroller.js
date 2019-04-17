//品牌控制层  Controller
app.controller('BrandController', function ($scope, $http, brandService,$controller) {

    $controller('baseController',{$scope:$scope});//继承

    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        )
    }
    // //分页控件配置
    // $scope.paginationConf = {
    //     currentPage: 1,
    //     totalItems: 5,
    //     itemsPerPage: 10,
    //     perPageOptions: [10, 20, 30, 40, 50],
    //     onChange: function () {
    //         //刷新
    //         $scope.reloadList();
    //     }
    // }

    // //封装一个分页的方法
    // $scope.reloadList = function () {
    //     $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    // }

    // //分页的请求
    // $scope.findPage = function (pageNum, pageSize) {
    //     brandService.findPage(pageNum, pageSize).success(
    //         function (response) {
    //             $scope.list = response.rows;
    //             $scope.paginationConf.totalItems = response.total;
    //         }
    //     )
    // }
    //修改+添加
    $scope.save = function () {
        var Object = null;  //定义变量
        if ($scope.entity.id != null) {//判断id是否为空 是空就是添加 不是空就是修改
            Object = brandService.update($scope.entity);
        } else {
            Object = brandService.addBrand($scope.entity);
        }
        Object.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    //刷新
                    $scope.reloadList();
                    alert(response.message);
                } else {
                    alert(response.message);
                }
            }
        )
    }
    //修改回显根据id
    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    }
    // //选中的复选框 放入数组
    // $scope.selectids = [];
    // $scope.updateSelection = function ($event, id) {
    //     if ($event.target.checked) {
    //         $scope.selectids.push(id);
    //     } else {
    //         var idx = $scope.selectids.indexOf(id);
    //         $scope.selectids.splice(idx, 1);
    //     }
    // }

    //批量删除
    $scope.delete = function () {
        brandService.delete($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    //重新查询
                    //刷新
                    $scope.reloadList();
                    alert(response.message);
                } else {
                    alert(response.message);
                }
            }
        )
    }
    //设置查询条件为空
    $scope.seachEntity = {};
    //条件查询
    $scope.search = function (pageNum, size) {
        brandService.search(pageNum, size,$scope.seachEntity).success(
            function (response) {
                $scope.list = response.rows;//显示当前页数
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
});