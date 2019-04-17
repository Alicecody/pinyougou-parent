//服务层
app.service('itemCatService',function($http){


    //根据上级ID查询下级列表
    this.findByParentId=function(parentId){
        return $http.get('../itemCat/findByParentId.do?parentId='+parentId);
    }

});
