//品牌服务层   Service
app.service("brandService", function ($http) {
    //查找列表
    this.findAll = function () {
        return $http.get('../brand/findAll.do');
    }

    //分页
    this.findPage = function (pageNum, pageSize) {
        return $http.get("../brand/findPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize);
    }

    //添加
    this.addBrand = function (entity) {
        return $http.post('../brand/addBrand.do', entity);
    }

    //修改回显
    this.findOne = function (id) {
        return $http.post("../brand/findOne.do?id=" + id);
    }

    //修改数据
    this.update = function (entity) {
        return $http.post('../brand/update.do', entity);
    }

    //批量删除
    this.delete = function (id) {
        return $http.get("../brand/del.do?ids=" + id);
    }
    //条件查询
    this.search = function (pageNum,pageSize,seachEntity) {
        return $http.post("../brand/search.do?pageNum=" + pageNum + "&pageSize=" + pageSize,seachEntity);
    }

    //查找品牌下拉框
    this.selectOption=function(){
        return $http.get('../brand/selectOption.do');
    }

});