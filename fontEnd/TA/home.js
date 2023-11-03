function home(){
document.getElementById('body').innerHTML = `
<div class="content-wrapper">
    <!-- content-header -->
    <section class="content-header">
        <h1>
            Home
            <small>Information</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/home/"><i class="fa fa-dashboard"></i> Home</a></li>
        </ol>
    </section>
    <!-- content-header /-->
    <!-- content -->
    <section class="content">
        <div class="row">
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-aqua">
                    <div class="inner">
                        <h3 th:text="${countOrder}"></h3>
                        <p>Đơn hàng</p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-bag"></i>
                    </div>
                    <a class="small-box-footer" data-bs-toggle="modal" data-bs-target="#myModal1">
                        <button class="info">Chi tiết</button>
                        <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-green">
                    <div class="inner">
                        <h3>53<sup style="font-size: 20px">%</sup></h3>

                        <p>Tỷ lệ chuyển đổi</p>
                    </div>
                    <div class="icon">
                        <i class="fa fa-first-order"></i>
                    </div>
                    <a class="small-box-footer">
                        <button class="info" id="btn-modal">Chi tiết</button>
                        <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-yellow">
                    <div class="inner">
                        <h3 th:text="${countCustomer}"></h3>
                        <p>Người dùng mới</p>
                    </div>
                    <div class="icon">
                        <i class="fa fa-user"></i>
                    </div>
                    <a class="small-box-footer" data-bs-toggle="modal" data-bs-target="#myModal3">
                        <button class="info" id="btn-modalUser">Chi tiết</button>
                        <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div>
            <!-- ./col -->
            <div class="col-lg-3 col-xs-6">
                <!-- small box -->
                <div class="small-box bg-red">
                    <div class="inner">
                        <h3 th:text="${countJob}"></h3>
                        <p>Công việc</p>
                    </div>
                    <div class="icon">
                        <i class="fa fa-calendar"></i>
                    </div>
                    <a class="small-box-footer" data-bs-toggle="modal" data-bs-target="#myModal2">
                        <button class="info">Chi tiết</button>
                        <i class="fa fa-arrow-circle-right"></i></a>
                </div>
            </div>
            <!-- ./col -->
        </div>
        <!-- /.row -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <i class="fa fa-users"></i>
                <h3 class="box-title">Account</h3>
            </div>
            <div class="box-body">
                <div class="table-box">
                    <table id="example" class="table table-bordered table-striped table-hover dataTable">

                    </table>
                </div>
            </div>
        </div>
    </section>
</div>
`}