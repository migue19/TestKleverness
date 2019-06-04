package mx.com.nut.testcleverness.data.entity.Response

data class ResponseHub(
    var code : String?,
    var message : String?,
    var errorId: String?,
    var hub: HubModel?
)



data class HubModel(
    var position: Int?,
    var isOn: Boolean?,
    var aliasHub: String?,
    var mac: String?,
    var modelId: String?,
    var deviceStateId: Int?,
    var device: List<DeviceModel>

)


data class DeviceModel(
    var position: Int?,
    var aliasDevice: String?
)